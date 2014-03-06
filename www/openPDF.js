  window.openPDF = function(str, callback) {   
      cordova.exec(callback, function(err) {
          console.log('Error PdfViewer.');
      }, "PdfViewer", "openPDF", [ str ]);
  };