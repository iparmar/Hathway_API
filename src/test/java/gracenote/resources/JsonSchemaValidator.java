package gracenote.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.io.File;

/**
 * Created by iparmar on 02/09/2018.
 */
public class JsonSchemaValidator {
    public static boolean validate(String response, String schemaFileName) {
        try {
            File schemaFile = new File(schemaFileName);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode responseJsonNode = mapper.readTree(response);

            JsonNode schemaJsonNode = mapper.readTree(schemaFile);
            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema jsonSchema = factory.getJsonSchema(schemaJsonNode);
            ProcessingReport processingReport = jsonSchema.validate(responseJsonNode);

            if (!processingReport.isSuccess()) {
                System.out.println("Exception" + processingReport.toString());
                return false;
            } else
                return processingReport.isSuccess();
        } catch (Exception e) {
            return false;
        }
    }
}
