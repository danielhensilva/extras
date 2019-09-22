package controllers;

import com.google.inject.Inject;
import models.Product;
import models.ProductsService;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.products.*;

import java.util.List;
import java.util.Optional;

public class ProductsController extends Controller {

    private final Form<Product> productForm;
    private final ProductsService productsService;

    @Inject
    public ProductsController(final FormFactory formFactory) {
        this.productForm = formFactory.form(Product.class);
        this.productsService = new ProductsService();
    }

    public Result list() {
        List<Product> products = this.productsService.findAll();
        return ok(list.render(products));
    }

    public Result create() {
        final Product product = new Product();
        final Form<Product> form = this.productForm.fill(product);
        return ok(details.render(form));
    }

    public Result details(String ean) {
        final Optional<Product> match = this.productsService.findByEan(ean);

        if (match.isPresent()) {
            final Product product = match.get();
            final Form<Product> form = this.productForm.fill(product);
            return ok(details.render(form));
        }

        return notFound("Product " + ean + " does not exist.");
    }

    public Result save() {
        final Http.Request request = request();
        Form<Product> form = this.productForm.bindFromRequest(request);

        if (form.hasErrors()) {
            flash("error", "Please correct the form below");
            return badRequest(details.render(form));
        }

        Product product = form.get();
        final String previousEan = request.body().asFormUrlEncoded().get("_ean")[0];
        final String newEan = product.getEan();

        product.setEan(previousEan);
        this.productsService.save(product);
        product.setEan(newEan);

        flash("success", "Successfully saved product " + product);
        return redirect(routes.ProductsController.list());
    }

}
