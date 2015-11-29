$(document).ready(function () {
    $('#lg-menu a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })

    $('#lg-menu').bind('show', function (e) {
        paneID = $(e.target).attr('href');
        src = $(paneID).attr('data-src');
        // if the iframe hasn't already been loaded once
        if ($(paneID + " iframe").attr("src") == "")
        {
            $(paneID + " iframe").attr("src", src);
        }
    });
});
