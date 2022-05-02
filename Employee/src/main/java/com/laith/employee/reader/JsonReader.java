package com.laith.employee.reader;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import com.laith.employee.enums.EmploymentType;
import com.laith.employee.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class JsonReader implements FilesReader {

    @Override
    public List<Employee> getEmployees() throws IOException {
        log.info("Inside the getEmployees method in class JsonReader that convert the json into list of employee");
        InputStream inputStream = Resources.getResource("employees.json").openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        Type listType = new TypeToken<ArrayList<Employee>>() {
        }.getType();
        return getGson().fromJson(json, listType);
    }

    private Gson getGson() {
        Gson gson = new GsonBuilder().registerTypeAdapter(EmploymentType.class,
                new TypeAdapter<EmploymentType>() {
                    @Override
                    public void write(JsonWriter jsonWriter, EmploymentType employmentType) throws IOException {
                        jsonWriter.value(employmentType.name().toLowerCase());
                    }
                    @Override
                    public EmploymentType read(com.google.gson.stream.JsonReader jsonReader) throws IOException {
                          return EmploymentType.valueOf(jsonReader.nextString().toUpperCase());
                    }

                }).create();
        return gson;
    }
}
