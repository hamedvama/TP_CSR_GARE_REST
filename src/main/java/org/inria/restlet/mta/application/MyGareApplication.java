package org.inria.restlet.mta.application;


import org.inria.restlet.mta.resources.TrainsResource;
import org.inria.restlet.mta.resources.VoyageursResource;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 *
 * Application.
 *
 * @author diakite
 *
 */
public class MyGareApplication extends Application
{

    public MyGareApplication(Context context)
    {
        super(context);
    }

    @Override
    public Restlet createInboundRoot()
    {
        /*Define router of application*/
        Router router = new Router(getContext());
        router.attach("/trains", TrainsResource.class);
        router.attach("/voyageurs", VoyageursResource.class);
        return router;
    }
}