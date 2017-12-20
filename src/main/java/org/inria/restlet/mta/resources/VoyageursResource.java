package org.inria.restlet.mta.resources;

import java.util.ArrayList;
import java.util.Collection;

import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.internals.Voyageur;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 * Resource exposing the voyageurs
 *
 * @author diakite
 * @author khabaz
 *
 */
public class VoyageursResource extends ServerResource
{
    /** Backend. */
    private Backend backend_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public VoyageursResource()
    {
        super();
        backend_ = (Backend) getApplication().getContext().getAttributes()
                .get("backend");
    }

    /**
     *
     * Returns the list of all the voyageurs
     *
     * @return  JSON representation of the voyageurs
     * @throws JSONException
     */
    @Get("json")
    public Representation getVoyageurs() throws JSONException
    {
        Collection<Voyageur> voyageurs = backend_.getDatabase().getVoyageurs();
        Collection<JSONObject> jsonVoyageurs = new ArrayList<JSONObject>();

        for (Voyageur voyageur : voyageurs)
        {
            JSONObject current = new JSONObject();
            current.put("name", voyageur.getNom());
            current.put("Etat",voyageur.getStateV());
            jsonVoyageurs.add(current);

        }
        JSONArray jsonArray = new JSONArray(jsonVoyageurs);
        return new JsonRepresentation(jsonArray);
    }

    @Post("json")
    public Representation createVoyageur(JsonRepresentation representation)
            throws Exception
    {
        JSONObject object = representation.getJsonObject();
        String name = object.getString("name");
        //int age = object.getInt("age");

        // Save the voyageur
        Voyageur voyageur = backend_.getDatabase().createVoyageur(name);

        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("name", voyageur.getNom());
        resultObject.put("etat", voyageur.getStateV());
        JsonRepresentation result = new JsonRepresentation(resultObject);
        return result;
    }

}