var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'Sam', 'coolMethod', [arg0]);
};
exports.startTrip = function(driverID, taskIDs, success, error) {
    exec(success, error, "sam", "startTrip", [driverID, taskIDs]);
};
