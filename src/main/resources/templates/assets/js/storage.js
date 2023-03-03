$(document).ready(function(e) {
    $('.generatePasswordButton').click(function() {
        let forId= $(this).data("for");
        $('#'+forId).val(generatePassword(16));
        $('#'+forId).trigger("input");
    });
    $('.passwordInputModal').on('input', function () {

        let currentPassword = $(this).val();
        let forId = $(this).data("for");
        let passwordScore = getPasswordScore(currentPassword);
        $('.PasswordScoreModal', $(forId)).text(passwordScore);
        $('.passwordStrongTextModal', $(forId)).text(getPasswordStrengthFromScore(passwordScore));
        $('.passwordStrongTextModal', $(forId)).css("color", getPasswordColorFromScore(passwordScore));
        console.log(currentPassword);
    });
    $('.changePasswordButton').click(function() {
        $('#ChangePasswordModalCurrentUser').val($(this).data("userid"));
    });
});

function generatePassword(length) {
    var charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-+*!@#$%^&()";
    retVal = "";
    for (var i = 0, n = charset.length; i < length; ++i) {
        retVal += charset.charAt(Math.floor(Math.random() * n));
    }
    return retVal;
}

function getPasswordScore(password) {
    var score = 0;
    if (!password)
        return score;

    // award every unique letter until 5 repetitions
    var letters = new Object();
    for (var i=0; i<password.length; i++) {
        letters[password[i]] = (letters[password[i]] || 0) + 1;
        score += 5.0 / letters[password[i]];
    }

    // bonus points for mixing it up
    var variations = {
        digits: /\d/.test(password),
        lower: /[a-z]/.test(password),
        upper: /[A-Z]/.test(password),
        nonWords: /\W/.test(password),
    }

    variationCount = 0;
    for (var check in variations) {
        variationCount += (variations[check] == true) ? 1 : 0;
    }
    score += (variationCount - 1) * 10;

    return parseInt(score);
}

function getPasswordColorFromScore(score){
    var color = new Array();
    color[0] = "#ff0000";
    color[1] = "#ff5f5f";
    color[2] = "#ffafaf";
    color[3] = "#ffff00";
    color[4] = "#00ff00";
    color[5] = "#00ff00";

    if (score > 80) {
        return color[5];
    } else if (score > 60) {
        return color[4];
    } else if (score >= 30) {
        return color[3];
    } else if (score >= 15) {
        return color[2];
    } else if (score >= 0) {
        return color[1];
    } else {
        return color[0];
    }
}

function getPasswordStrengthFromScore(score) {
    var desc = new Array();
    desc[0] = "very Weak";
    desc[1] = "weak";
    desc[2] = "better";
    desc[3] = "medium";
    desc[4] = "strong";
    desc[5] = "strongest";

    if (score > 80) {
        return desc[5];
    } else if (score > 60) {
        return desc[4];
    } else if (score >= 30) {
        return desc[3];
    } else if (score >= 15) {
        return desc[2];
    } else if (score >= 0) {
        return desc[1];
    } else {
        return desc[0];
    }
}
