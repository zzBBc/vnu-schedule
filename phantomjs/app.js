var page = require('webpage').create();
page.viewportSize = { width: 1024, height: 768 };
console.log(page.settings.userAgent);
page.settings.userAgent = 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.98 Safari/537.36';
console.log(page.settings.userAgent);
page.open("http://dangkyhoc.vnu.edu.vn/dang-nhap", function(status) {

  if (status === "success") {
    console.log(status);
    page.render("page.png");
    page.evaluate(function() {
        document.querySelector('input[id="LoginName"]').value="";
        document.querySelector('input[id="Password"]').value="";
        document.querySelector('button[class="btn btn-success"]').click();
    });
     window.setTimeout(function() {
       page.render("page2.png");
       page.evaluate(function() {
        document.location.href='http://dangkyhoc.vnu.edu.vn/dang-ky-mon-hoc-nganh-1#';
    });
       window.setTimeout(function() {
       page.render("page3.png");

     
       var fs = require('fs');
     var result = page.evaluate(function() {
      //return document.querySelector('#divDSDK').outerHTML;
      return document.querySelector('#divDSDK').textContent;
      
//       var mangTr=document.querySelectorAll('tr');
// for (var i = 0; i <= mangTr.length - 1; i++) {
//   //console.log(mangTr[i].innerHTML);

//   var mangTd=mangTr[i].querySelectorAll('td');
//   console.log(mangTd[1].innerHTML);
// for (var j = 0; j <= mangTd.length - 1; j++) {
//   console.log(mangTd[j].innerHTML);
  


  
// }

// }
    });
        fs.write('content.txt',result,'w');


       phantom.exit();
       }, 5000);
    }, 5000);
  }
});