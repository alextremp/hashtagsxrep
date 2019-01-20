function hashtagCount(ht, ok) {
    var input = ht.replace('#', '');
    $.ajax({
        url: "/hashtag/" + input + "/count",
        success: ok,
        dataType: 'json'
    });
}

function submitProposal() {
    var hashtag = $('#hashtag');
    hashtagCount(hashtag.val(), function(response) {
        var form = $("#proposalForm");
        if (response.accepted) {
            form.attr('onsubmit', '');
            form.submit();
        } else {
            var validation = $('#hashtag_validation');
            validation.addClass('error');
            validation.text(response.reason);
            removeValidation('#hashtag');
            hashtag.get(0).scrollIntoView();
        }
    });
    return false;
}

function removeValidation(selector) {
    var timeoutId = setTimeout(function() {
        var validation = $(selector+'_validation');
        validation.removeClass('error');
        validation.text('');
        clearTimeout(timeoutId);
    }, 10000);
}

console.log(">> app js");