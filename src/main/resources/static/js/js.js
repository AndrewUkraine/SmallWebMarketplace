;(function () {

    //get delete button

    var btnElements = document.getElementsByClassName('remove-pic-btn');
    // elem.parentNode.removeChild(elem);


    for (var i = 0; i < btnElements.length; i++) {
        //add onClick function to button
        var btnElement = btnElements[i];

        btnElement.addEventListener('click', function (ev) {
            ev.preventDefault();
            // alert(btnElements.dataset.img);
            //get pictureId of the button
            // debugger;
            var imgId = this.dataset.img;

            //todo remove picture by id
            var xhr = new XMLHttpRequest();
            xhr.open('GET', '/offer/image/' + imgId , false);
            xhr.send();


            //find and remove div by pictureId
            document.getElementById(imgId).remove();

        });
    }


})();
