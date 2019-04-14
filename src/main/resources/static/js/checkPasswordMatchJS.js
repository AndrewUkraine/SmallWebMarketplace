
function checkPasswordMatch() {
   // debugger;
    var password = $("#txtNewPassword").val();
    var confirmPassword = $("#txtConfirmPassword").val();

    if (password != confirmPassword){
        $("#divCheckPasswordMatch").html("<div style='color:red'>Passwords do not match!</div>");
        document.getElementById("txtNewPassword").value = null;
        document.getElementById("txtConfirmPassword").value = null;}


    else{
        $("#divCheckPasswordMatch").html("<div style='color:green'>Passwords match.</div>");
    }
}

$(document).ready(function () {
    $("#txtNewPassword, #txtConfirmPassword").keyup(checkPasswordMatch);
});
