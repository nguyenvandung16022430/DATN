$(document).ready(function() {
    $(".btn-add-node").on("click",function(){
        var pdInfo =  document.getElementById("node-input-name").value;
        NProgress.start();
        var linkGet = "/api/node/create/"+ pdInfo;
        axios.post(linkGet).then(function(res){
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
                );
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
    })