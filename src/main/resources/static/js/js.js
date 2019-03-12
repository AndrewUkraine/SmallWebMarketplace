;(function () {

    //get delete button

    var btnElem = document.getElementById('remove-pic-btn');
    // elem.parentNode.removeChild(elem);


    //add onClick function to button
    btnElem.addEventListener('click', function (ev) {
        ev.preventDefault();
        alert(btnElem.dataset.img);
        //get pictureId of the button
        var imgId = btnElem.dataset.img;

        //todo remove picture by id
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/offer/image/' + imgId , false);
        xhr.send();


        //find and remove div by pictureId
        document.getElementById(imgId).remove();

    })
})();


// (".clear_buton").click(function(event){
//     $(this).closest(".con_img").remove();
// });