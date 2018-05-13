const fs = require('fs');
const path = require('path');
const util = require('util');

(async function mainAsync() {
    
    try {

        const files = await (util.promisify(fs.readdir))('./files');
        files.forEach(async file => {
            
            const filePath = path.join('./files', file);
            const filePathBackup = filePath + '.bkp';

            const buffer = await (util.promisify(fs.readFile))(filePath);
            const truncatedBuffer = buffer.subarray(0, buffer.length/2);

            await (util.promisify(fs.writeFile))(filePathBackup, truncatedBuffer);
            await (util.promisify(fs.unlink))(filePath);
            await (util.promisify(fs.rename))(filePathBackup, filePath);

        });

    }
    catch (err) {
        console.error(err);
    }

})();