'use strict';

import {Router} from 'express';
import * as controller from './user.controller';
import * as auth from '../../components/auth/auth.service';

var router = new Router();

router.get('/', auth.isAuthenticated, controller.show);
router.post('/', controller.create);

module.exports = router;
