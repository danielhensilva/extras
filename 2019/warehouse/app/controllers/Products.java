package controllers;

import models.Product;
import models.ProductsService;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class Products extends Controller {

    public Result list() {
        List<Product> products = new ProductsService().findAll();
        return ok(views.html.products.list.render(products));
    }

    public Result create() {
        return TODO;
    }

    public Result details(String ean) {
        return TODO;
    }

    public Result save() {
        return TODO;
    }

}
