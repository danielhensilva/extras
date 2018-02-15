import * as express from "express";
import * as compression from "compression";
import * as bodyParser from "body-parser";
import * as logger from "morgan";
import * as dotenv from "dotenv";
import * as path from "path";
import * as expressValidator from "express-validator";

const app = express();

dotenv.config({ path: ".env.example" });

// Express configuration
app.set("port", 3000);
app.use(compression());
app.use(logger("dev"));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(expressValidator());

/**
 * API routes.
 */
app.use("/auth", require("./auth"));
app.listen(() => console.log("App running..."));