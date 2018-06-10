var map, layer;

var backgroundMap = {};
var urls = {};
urls.map_imgs = "http://10.197.1.51:31001/MapService/getGdp";

backgroundMap.init = function(mapGdpURL,zoom) {
	
	if(mapGdpURL!=null){
		urls.map_imgs = mapGdpURL;
	}
	
	if(zoom!=null){
		zoom = 4;
	}
	
	map = new SuperMap.Map("map", {
		controls: [
			new SuperMap.Control.ScaleLine(),
			new SuperMap.Control.MousePosition(),
			new SuperMap.Control.Navigation({
				dragPanOptions: {
					enableKinetic: true
				}
			})
		],
		allOverlays: false
	});

	layer = new SuperMap.Layer.CloudLayer({
		url: urls.map_imgs
	});

	map.addLayers([layer]);
	map.setCenter(new SuperMap.LonLat(12080677, 4591416), 1);
}
