package org.inria.restlet.mta.resources;

import java.util.ArrayList;
import java.util.Collection;

import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.internals.Train;
import org.inria.restlet.mta.internals.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 * Resource exposing the trains
 *
 * @author diakite
 * @author khabaz
 *
 */
public class TrainsResource extends ServerResource
{
    /** Backend. */
    private Backend backend_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public TrainsResource()
    {
        super();
        backend_ = (Backend) getApplication().getContext().getAttributes()
                .get("backend");
    }

    /**
     *
     * Returns the list of all the trains
     *
     * @return  JSON representation of the trains
     * @throws JSONException
     */
    @Get("json")
    public Representation getTrains() throws JSONException
    {
        Collection<Train> trains = backend_.getDatabase().getTrains();
        Collection<JSONObject> jsonTrains = new ArrayList<JSONObject>();

        for (Train train : trains)
        {
            JSONObject current = new JSONObject();
            current.put("name", train.getTrainName());
            current.put("vitesse", train.getVitesse());
            current.put("Etat",train.getStateTrain());
            jsonTrains.add(current);

        }
        JSONArray jsonArray = new JSONArray(jsonTrains);
        return new JsonRepresentation(jsonArray);
    }

    @Post("json")
    public Representation createTrain(JsonRepresentation representation)
            throws Exception
    {
        JSONObject object = representation.getJsonObject();
        String name = object.getString("name");
        //int age = object.getInt("age");

        // Save the user
        Train train = backend_.getDatabase().createTrain(name);

        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("name", train.getTrainName());
        resultObject.put("vitesse", train.getVitesse());
        resultObject.put("Etat", train.getStateTrain());
        JsonRepresentation result = new JsonRepresentation(resultObject);
        return result;
    }

}