'use strict';

import {Router} from 'express';
import * as service from './auth.service';

var router = new Router();

router.post('/', service.authenticate);

module.exports = router;
