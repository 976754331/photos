function loadImage(typeId) {
	var types = {
		typeId: typeId
	};
	simpAjax(Url + "/picture/list.do", types, function(result) {
		if(result.rtn_code == 0) {
			var pics = result.data;
			var strHtml = "";
			var timeHtml = "";
			if(pics.length==0){
				strHtml += `<div class="mui-slider-item">
				<div style="margin-top: 3rem;margin-bottom: 1rem;">
				<span><h2>暂无图片</h2></span>
				</div>	
				<button class="toUpload" data-type=${typeId}><h4>点我上传图片</h4></button>
				</div>`				
				$(".mui-slider-group").append(strHtml);
				strHtml = "";
			}else{
				for(var i = 0; i < pics.length; i++) {
					if(pics[i].intro==null){pics[i].intro="暂无简介"}else{pics[i].intro}
					if(pics[i].desc==null){pics[i].desc="暂无描述"}else{pics[i].desc}
					if(pics[i].addr==null){pics[i].addr="暂无地点"}else{pics[i].addr}
					if(pics[i].attachment_id==null){pics[i].attachment_id="01854199a7334165b55db9dffabac2a0"}else{pics[i].attachment_id}
					$("#timeAddr").empty();
					strHtml += `<div class="mui-slider-item">
					<div style="margin-top: 3rem;">
					<span><img data-id=${pics[i].pic_id} src="${picSrc}${pics[i].attachment_id}" alt="" /></span>
					</div>
					<form >
					<div class="mui-detail-information">
					<h4>图片简介</h4>
					<textarea>${pics[i].intro}</textarea>
					</div>
					<div class="mui-detail-information">
					<h4>图片描述</h4>
					<textarea>${pics[i].desc} </textarea>
					</div>					
					<div  style="position: relative;margin-bottom:1rem">
					<span><h4>时间：</h4> <input type="text" value=${pics[i].shoot_date}  style="height:1.9rem; width:13rem;position: absolute;top: -0.4rem; left:3.2rem"/> </span> 	
					</div>
					<div  style="position: relative;margin-top:1rem">
					<span><h4>地点：</h4> <input type="text" value=${pics[i].addr}  style="height:1.9rem; width:13rem;position: absolute;top: -0.4rem; left:3.2rem"/> </span> 	
					</div>
					<div style="margin:1rem">
					<input type="button" value="修改" class="modifyInfo">
					<input type="button" value="上传" class="toUpload" data-type=${typeId}>
					</div>
					</form>
					
					</div>`
					$(".mui-slider-group").append(strHtml);
					strHtml = "";
				}
			}
			

		} else {
			mui.toast(result.msg);
		}
	})

}

//页面加载完成事件
$(function(){
	//修改页面信息
	mui('.mui-slider-group').on('tap', '.modifyInfo', function(e) {
		var divs = $(this).parent().parent().children();
		var intro = $(divs[0]).children('textarea').val();
		var desc = $(divs[1]).children('textarea').val();
		var time = $(divs[2]).children().children('input').val();
		var attr = $(divs[3]).children().children('input').val();
		
	})
	
	//上传图片按钮
	mui('.mui-slider-group').on('tap', '.toUpload', function(e) {
	    var typeIdThis = $(this).attr("data-type");
		var popHtml = `
				<div class="mui-input-group" style="margin:0;padding:0;position: relative;">
					<div class="mui-input-row mui-radio mui-left" style="margin:0;padding:0;">
						<label style="position: absolute;top: -0.14rem;left:-1rem">从相册中选取照片</label>
						<input name="radio1" type="radio" value="0" data-type=${typeIdThis}>
					</div>
					<div class="mui-input-row mui-radio mui-left" style="margin:0;padding:0;">
						<label style="position: absolute;top: -0.14rem;left:-2.5rem">拍照上传</label>
						<input name="radio1" type="radio" checked value="1" data-type=${typeIdThis}>
					</div>					
				</div>
			`;
		
		mui.confirm(popHtml, '提示',  function(e) {
			if (e.index == 1) {
	            //确认选择
	            var getRadio = $(".mui-input-group input:checked").val();
	            var secType = $(".mui-input-group input:checked").attr("data-type");
	            if(getRadio == 0){ //从相册中选取照片

                    navigator.camera.getPicture(onSuccess, onFail, {
                        quality: 100,
                        sourceType: Camera.PictureSourceType.PHOTOLIBRARY,
                        destinationType: Camera.DestinationType.FILE_URI
                        //destinationType: Camera.DestinationType.FILE_URI,
                        //targetWidth: 100,
                        //targetHeight: 100
                    });

                    function onSuccess(imageURI) {
                        uploadHead(imageURI,secType);
                    }

                    function onFail(message) {
                        console('Failed because: ' + message);
                    }

	            }else{ //拍照上传

                    navigator.camera.getPicture(takeSuccess, takeFail, {
                        quality: 100,
                        destinationType: Camera.DestinationType.FILE_URI
                    });

                    function takeSuccess(imageURI) {
                        uploadHead(imageURI,secType);
                    }

                    function takeFail(message) {
                        console('Failed because: ' + message);
                    }

	            }
	                     
	        } else {
	            //取消
	        }
		})
	})
	
})

//添加照片
function uploadHead(imgPath,secType) {
   var image = new Image();
    image.src = imgPath;
    var base64 = "";
    image.onload = function(){
        imageCompressQuantity(imgPath,secType);
    }

}

//将图片压缩转成base64
function getBase64Image(img) {
    var square = 700;
    var canvas = document.createElement("canvas");
    var width = img.width;
    var height = img.height;
    canvas.width = square;
        canvas.height = square;
    var ctx = canvas.getContext("2d");
    ctx.clearRect(0, 0, square, square);

    ctx.drawImage(img, 0, 0, width, height); /*绘图*/
    var dataURL = canvas.toDataURL("image/png", 0.8);
    return dataURL.replace("data:image/png;base64,", "");
}

//图片质量压缩
function imageCompressQuantity(url,secType) {
    navigator.ImageCompass.imageCompressQuantity(onSuccess1, onFail1, {
        dataType: 'DATA_TYPE_BASE64',
        srcPath: url,
        //height: 100,
        //width: 100,
        bitmapSize: 20000,
        comquantity: 100
    });
    function onSuccess1(imageURI) {
        //数据提交
        var params = {
			imgUrl:imageURI.IMG_COMPASS_DATA,
			imgPath:url,
            typeId:secType
		}
		simpAjax(Url + "/file/uploadPic.do", params, function(result) {
			if(result.rtn_code == 0) {   
                mui.toast("上传成功");                   
            } else {
                mui.toast(result.msg);
            }
        });
    }
    function onFail1(message) {
        console.log('Failed because: ' + message);
    }
}
