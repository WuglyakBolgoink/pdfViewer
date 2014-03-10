window.openPDF = function (parameters, callback) {
    cordova.exec(function () {
    }, function () {
    }, "PdfViewer", "openPDF", [ parameters ]);
};
