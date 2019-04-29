
function checkPasswordMatch() {
    debugger;
    var password = $("#updateNewPassword").val();
    var confirmPassword = $("#matchingPassword").val();

    if (password != confirmPassword){
        $("#divCheckPasswordMatch").html("<div style='color:red'>Passwords do not match!</div>");
        document.getElementById("updateNewPassword").value = null;
        document.getElementById("matchingPassword").value = null;}


    else{
        $("#divCheckPasswordMatch").html("<div style='color:green'>Passwords match.</div>");
    }
}

$(document).ready(function () {
    $("#txtNewPassword, #txtConfirmPassword").keyup(checkPasswordMatch);
});
