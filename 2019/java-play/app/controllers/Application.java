package controllers;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public Result index(final String name) {
        if (name == null || name.isEmpty()) {
            return ok("Hello, world");
        }
        return ok(hello.render(name));
    }

}
