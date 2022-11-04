package QuestionBank;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

/**
 * GsonInstanceCreator is used by Gson to create Questions from abstract Question Class
 */
public class GsonInstanceCreator implements InstanceCreator<Questions> {

    public GsonInstanceCreator() {
    }

    @Override
    public Questions createInstance(Type type) {
        // create new object with our additional property
        Questions userContext = new Questions() {
        };

        // return it to gson for further usage
        return userContext;
    }
}