package com.pdf.servlets;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.util.json.JSONArray;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
//import com.amazonaws.util.json.JSONArray;
//import com.amazonaws.util.json.JSONException;
//import com.amazonaws.util.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 *
 * @author Dries Horions <dries@quodlibet.be>
 */
public class d2csv
{
    List<String> recordList = new ArrayList();
    public  String getData(JSONObject config)
    {
        if (validateConfig(config))
        {
            try
            {
                BasicAWSCredentials awsCreds = new BasicAWSCredentials((String) config.get("accessKeyId"), (String) config.get("secretAccessKey"));
                AmazonDynamoDBClient client = new AmazonDynamoDBClient(awsCreds);

                Region r = Region.getRegion(Regions.fromName((String) config.get("region")));
                client.setRegion(r);
                //DynamoDB dynamoDB = new DynamoDB(client);
                ScanRequest scanRequest = new ScanRequest().withTableName((String) config.get("tableName"));
                if (config.has("filterExpression"))
                {
                    scanRequest.withFilterExpression((String) config.get("filterExpression"));
                }
                if (config.has("expressionAttributeValues"))
                {
                    JSONArray evals = (JSONArray) config.get("expressionAttributeValues");
                    Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
                    for (int i = 0; i < evals.length(); i++)
                    {
                        JSONObject val = (JSONObject) evals.get(i);
                        String type = val.getString("type");
                        AttributeValue av = new AttributeValue();
                        switch (type)
                        {
                            case "N":
                                av.withN(val.getString("value"));
                                break;
                            case "S":
                                av.withS(val.getString("value"));
                                break;
                            default:
                                //handle all non numeric as String
                                av.withS(val.getString("value"));
                        }
                        expressionAttributeValues.put(val.getString("name"), av);
                    }
                    scanRequest.withExpressionAttributeValues(expressionAttributeValues);
                }
                if (config.has("expressionAttributeNames"))
                {
                    JSONArray evals = (JSONArray) config.get("expressionAttributeNames");
                    Map<String, String> expressionAttributeNames = new HashMap<String, String>();
                    for (int i = 0; i < evals.length(); i++)
                    {
                        JSONObject val = (JSONObject) evals.get(i);
                        expressionAttributeNames.put(val.getString("name"), val.getString("value"));
                    }
                    scanRequest.withExpressionAttributeNames(expressionAttributeNames);
                }
                ScanResult result = client.scan(scanRequest);
                //A map to hold all unique columns that were found, and their index
                HashMap<String, Integer> columnMap = new HashMap();
                //A map to hold all records
                int i=0;
                for (Map<String, AttributeValue> item : result.getItems())
                {
                    StringBuilder record = new StringBuilder();
                    handleMap("", item, columnMap, record);
                    if(i==0){
                        Set<String> s=columnMap.keySet();
                        StringBuilder header=new StringBuilder();
                        for(String str:s){
                            header.append(str+",");
                        }
                        recordList.add(header.toString());
                        i++;
                    }
                    recordList.add(record.toString());
                }
               
                    //Write the records    
            }
            catch (JSONException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
       String data= getListAsCsvString(recordList);
        return data;
    }
    private void handleMap(String path, Map<String, AttributeValue> item, HashMap<String, Integer> columnMap,StringBuilder record)
    {
        for (String key : item.keySet())
        {
            String keyName = key;
            if (!path.isEmpty())
            {
                keyName = path + "." + key;
            }

            String type = "";
            if (item.get(key).getS() != null )
            {
                type = "S";
            }
            if (item.get(key).getN() != null)
            {
                type = "N";
            }
            if (item.get(key).getM() != null )
            {
                type = "M";
            }
            if (item.get(key).getL() != null)
            {
                type = "L";
            }
            if (item.get(key).getBOOL() != null)
            {
                type = "B";
            }
            //Add as column if it's not a list or map
            if (!type.equals("M") & !type.equalsIgnoreCase("L") & !columnMap.containsKey(keyName))
            {
                //Add newly discovered column
                columnMap.put(keyName, columnMap.size());
            }

            switch (type)
            {
                case "S":
                    record.append(item.get(key).getS()+",");
                    break;
                case "N":
                    record.append( item.get(key).getN()+",");
                    break;
                case "B":
                    record.append(item.get(key).getBOOL().toString()+",");
                    break;
                case "M":
                    Map< String, AttributeValue> a = (Map< String, AttributeValue>) item.get(key).getM();
                    handleMap(keyName, a, columnMap, record);
                    break;
                case "L":
                    Map< String, AttributeValue> ml = new HashMap();
                    List<AttributeValue> l = item.get(key).getL();
                    for (AttributeValue v : l)
                    {
                        ml.put("" + ml.size(), v);
                    }
                    handleMap(keyName, ml, columnMap, record);
                    break;
                default:
                    System.out.println(keyName + " : \t" + item.get(key));
            }
        }
    }
    private Boolean validateConfig(JSONObject config)
    {
        Boolean valid = true;
        if (!config.has("accessKeyId"))
        {
            System.out.println("config parameter 'accessKeyId' is missing.");
            valid = false;
        }
        if (!config.has("secretAccessKey"))
        {
            System.out.println("config parameter 'secretAccessKey' is missing.");
            valid = false;
        }
        if (!config.has("region"))
        {
            System.out.println("config parameter 'region' is missing.");
            valid = false;
        }
        if (!config.has("tableName"))
        {
            System.out.println("config parameter 'tableName' is missing.");
            valid = false;
        }
        return valid;
    }
    public String getListAsCsvString(List<String> list){
         
        StringBuilder sb = new StringBuilder();
        for(String str:list){
            sb.append(str);
            sb.append(",");
            sb.append("\n");
        }
        return sb.toString();
    }
}
