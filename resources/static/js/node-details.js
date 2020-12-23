$(document).ready(function() {
    $(".toggle-node").on("click",function(){
        var pdInfo = $(this).data("id");
		document.getElementById("notification").style.display = 'block';
        NProgress.start();
        var linkGet = "/api/node/toggle/"+ pdInfo;
        axios.get(linkGet).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Success',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Fail',
                    res.data.message,
                    'error'
                ).then(function() {
                    location.reload();
                });
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    });
    $(".add-rule-node").on("click",function(){
        dataAddRule = {};
        dataAddRule.id = $(".toggle-node").data("id");
        dataAddRule.ruleS = document.getElementById("rule-value").value;

        console.log(dataAddRule.id);
        console.log(dataAddRule.ruleS);


        NProgress.start();

        var linkPost = "/api/node/addRule";

        axios.post(linkPost, dataAddRule).then(function(res){
            NProgress.done();
            if(res.data.success) {
                  swal(
                    'Success',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Fail',
                    res.data.message,
                    'error'
                ).then(function() {
                    location.reload();
                });
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    });

});
