(function($){
    var methods = {
        init: function(options){
            _default = $.extend(_default, options || {});
            // 创建Map实例
            _default.map = new BMap.Map(this.attr("id"), {minZoom:10});
            // 初始化地图,设置中心点坐标和地图级别
            _default.map.centerAndZoom(new BMap.Point(_default.center.lng, _default.center.lat), _default.zoom);
            //开启鼠标滚轮缩放
            _default.map.enableScrollWheelZoom(true);
         // 限制地图范围
//            var b = new BMap.Bounds(new BMap.Point(115.6816, 39.561166), new BMap.Point(117.005055,40.40766));
//            var b = new BMap.Bounds(new BMap.Point(116.027143, 39.772348),new BMap.Point(116.832025, 40.126349));
//            try {   
//                BMapLib.AreaRestriction.setBounds(_default.map, b);
//            } catch (e) {
//                console.log("百度地图范围限制发生错误");
//            }

            // 检索相应的数据
            methods._showMarker(_default);
            return this;
        },
        _getPositionAndZoom : function(map){
            var viewStatus = {};
            viewStatus.zoom = map.getZoom();
            var bound = map.getBounds();
            viewStatus.maxLng = bound.Sk.lng;
            viewStatus.maxLat = bound.Sk.lat;
            viewStatus.minLng = bound.hl.lng;
            viewStatus.minLat = bound.hl.lat;
            return viewStatus;
        },
        ajax: function(options) {
            $.extend(_default, options || {});
            $.ajax({
                url: _default.ajaxUrl,
                type: _default.ajaxMethod,
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                data: _default.ajaxData,
                dataType: _default.ajaxDataType,
                success: _default.ajaxSuccessMeth
            });
        },
        searchForResult: function(event, data, flg){
            if (event.RF != "centerAndZoom" || typeof event.target.map == "object" || flg){
                var option = {};
                option.ajaxData={};
                $.extend(true, option.ajaxData, data || {}, methods._getPositionAndZoom(_default.map))
                methods.ajax(option);
            }
        },
        getMap: function(){
            return _default.map;
        },
        showHouseList: function() {
            $.ajax({
                url: _default.houseListUrl,
                method: "post",
                data: {
                    houseIds: this.houseIds
                },
                dataType: "json",
                success: function(data) {
                    if(data.houseList){
                        data=data.houseList;
                    }
                    $(".info").animate({
                        left: 0
                    }, 1000);
                    $("#house-info>ul").empty();
                    for (var i in data) {
                        var liDom = $(
                                          "<a href='" + _default.houseInfoUrl + "?houseId=" + data[i].id + "' target='_blank'>"
                                        + "<li class='house-info'>                                         "
                                        + "    <ul>                                                        "
                                        + "        <li>                                                    "
                                        + "            <div class='first-child'>公寓品牌:</div>             "
                                        + "            <div class='last-child'>" + data[i].brandName + " </div>"
                                        + "        </li>                                                   "
                                        + "        <li>                                                    "
                                        + "            <div class='first-child'>户　　型:</div>             "
                                        + "            <div class='last-child'>" + data[i].layout + " </div>"
                                        + "        </li>                                                   "
                                        + "        <li>                                                    "
                                        + "            <div class='first-child'>价　　格:</div>             "
                                        + "            <div class='orange last-child'>" + data[i].price + "元/月</div>"
                                        + "        </li>                                                   "
                                        + "        <li>                                                    "
                                        + "            <div class='first-child'>数　　量:</div>             "
                                        + "            <div class='orange last-child'>" + data[i].totalNumber + "套</div>"
                                        + "        </li>                                                   "
                                        + "    </ul>                                                       "
                                        + "    <img src='/images/house/" + data[i].picPath + "'>           "
                                        + "</li></a>                                                       "
                                    );
                        liDom.appendTo($("#house-info>ul"));
                    } 
                }
            });
        },

        /*
         * TODO
         * 功能描述：把数据显示在地图上
         * 参数：
         *      data.markFlg: 根据不同的标识生成不同的覆盖物
         *      data.markList: 覆盖物相关信息数组
         *          data.markList[i].lng: 经度
         *          data.markList[i].lat: 纬度
         *          data.markList[i].zoneName: 当前区域的名称
         *          data.markList[i].houseNum: 当前区域房源数量
         *          data.markList[i].houseList: 当前位置房源数组
         *              data.markList[i].houseList[i]: 当前区域的所有房源相关信息
         *              data.markList[i].houseList[i].id: 房源ID
         *              data.markList[i].houseList[i].brandName: 房源所属公寓品牌
         *              data.markList[i].houseList[i].layout: 房源户型
         *              data.markList[i].houseList[i].price: 房源价格
         *              data.markList[i].houseList[i].img: 房源照片
         */
        _showMarker: function(data){
            if(data.mapResultVo){
                data= $.parseJSON(data.mapResultVo);
            }
            // 清除地图上的覆盖物 
            _default.map.clearOverlays();
            // 根据标识的不同生成不同的覆盖物
            var pDom = function(zoom, zoneName, houseNum) {
                if (zoneName != null && zoom == "1") {
                    return "<p class='grade" + zoom + "'>" + zoneName + "<br/>" + houseNum + "套</p>";
                } else {
                    return "<p class='grade" + zoom + "'>" + houseNum + "套</p>";
                }
            }
            var centerAndZoom = function (event){
                var p = this.getPosition();
                p = new BMap.Point(p.lng, p.lat);
                _default.map.centerAndZoom(p, _default.map.getZoom() + _default.zoomGrep);
            }
            if (data.markFlg == "行政区") {
                data.markFlg = "1"; 
                var size = new BMap.Size(-32, -37);
            } else if (data.markFlg == "街道" || data.markFlg == "商圈") {
                data.markFlg = "2"; 
                var size = new BMap.Size(-14, -31);
            } else if (data.markFlg == "小区") {
                data.markFlg = "3"; 
                var size = new BMap.Size(-9, -25);
            }
            for (var i in data.markList) {
                var opts = {
                    position : new BMap.Point(data.markList[i].lng, data.markList[i].lat),
                    offset: size
                }
                var label = new BMap.Label(pDom(data.markFlg, data.markList[i].zoneName,
                            data.markList[i].houseNum), opts);
                label.setStyle({
                    background: "transparent",
                    border: "0px"
                });
                _default.map.addOverlay(label);
                if (data.markFlg == "3") {
                    label.addEventListener("click", methods.showHouseList);
                    label.houseIds = data.markList[i].houseIds;
                } else {
                    label.addEventListener("click", centerAndZoom);
                }
            }
        }
    };
    var _default = {
        map: "",
        mapId: "map",
        center: {
            lng: 116.404,
            lat: 39.915
        },
        zoom: 11,
        zoomGrep: 3, // 地图每次放大递增等级
        houseListUrl: "", // 查询房屋列表URL
        houseInfoUrl: "", // 查询房屋详情URL
        ajaxUrl: "filter.json",
        ajaxData: {},
        ajaxMethod: "post",
        ajaxDataType: "json",
        ajaxSuccessMeth: methods._showMarker
    };
    $.fn.baiduMap = function(method) {
        if (methods[method] && method.charAt(0) != "_") {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1))
        } else if (typeof method === "object" || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error("map plugin don't has the method" + method);
        }
    }
})(jQuery);
