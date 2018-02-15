import * as express from "express";

const router = express.Router();

router.post("/signin", function (req: express.Request, res: express.Response) {
    res.status(200);
    res.json({
        "msg": "Hello, world!"
    });
});

router.post("/signout", function (req: express.Request, res: express.Response) {

});

module.exports = router;