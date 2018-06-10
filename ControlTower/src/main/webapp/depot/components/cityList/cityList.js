
var ajax_url_ = "http://10.186.25.37:8080/egisp/common/H01/cityList";
var ajax_query_citys_ = null;

/* ---------- citylist-entity ---------- */
/**
 * 城市列表组件 :城市控件
 */
var CityListEntity = React.createClass({
  	getInitialState: function() {
    	return {value:this.props.admincode};
  	},
  	handleClick: function(){
  		
  		var adcode = this.props.admincode;
  		
  		var requestBody = {
	        requestBody: JSON.stringify({
	            admincode: adcode,
	        })
	    };
	    // 在控件中加入请求
  		$.ajax({
  			type: "POST",
            url: ajax_url_,
            data: requestBody,
        	contentType: 'application/x-www-form-urlencoded; charset=utf-8',
            async: false,
            dataType: "json",
            success: function(data){
            	ajax_query_citys_ = data.results;
             }
  		});
  		
  		this.props.refreshCitys(ajax_query_citys_);
  	},
  	render: function() {
  		var city_entity_span_ = (
  			<a admincode={this.props.admincode} onClick={this.handleClick} >
  			{this.props.name}
  			</a>
  		);
  		return city_entity_span_;
  	}
});

/* ---------- citylist-details ---------- */
/**
 * 城市列表组件 :子城市列表控件
 */
var CityListChild = React.createClass({

	render: function() {
		var cl_citys_ = [];
		var value = this.props.citys.child;
		
		if(value.length==0){
			var idx_ = this.props.citys.parent.length - 1;
  			value.push(ajax_query_citys_.parent[idx_]);
		}

		for (var i=0;i<value.length;i++) {
			var item = value[i];
			cl_citys_.push(
				<li>
				<CityListEntity admincode={item.admincode} name={item.name} refreshCitys={this.props.refreshCitys.bind(this)} />
				</li>
			)
		}
		
		var cl_full_div = (
			<div id="cl-details">
			<dd>
				{cl_citys_}
			</dd>
			</div>
			);
		return cl_full_div;
	}
});

/* ---------- citylist-title ---------- */
/**
 * 城市列表组件 :城市标题列表控件
 */
var CityListTitle = React.createClass({
	render: function() {
		var cl_citys_ = [];
		var value = this.props.citys.parent;
		
		cl_citys_.push(
			<li>
				<CityListEntity admincode="000000" name="全国" refreshCitys={this.props.refreshCitys} />
			</li>
		)
		
		for (var i=0;i<value.length;i++) {
			var item = value[i];
			cl_citys_.push(
				<li>
				→ <CityListEntity admincode={item.admincode} name={item.name}  refreshCitys={this.props.refreshCitys} />
				</li>
			)
		}
		
		var cl_full_div = (
			<div id="cl-title">
			<span>当前位置:</span>
			<dd>
				{cl_citys_}
			</dd>
			</div>
			);
		return cl_full_div;
	}
});

/* ---------- citylist-head ---------- */
/**
 * 城市列表组件 :城市标题列表控件
 */
var CityListHead = React.createClass({
	render: function() {
	}
});

/* ---------- citylist ---------- */
/**
 * 城市列表组件
 */
var CityList = React.createClass({
	getInitialState: function() {
    	
    	var requestBody = {
	        requestBody: JSON.stringify({
	            admincode: "000000",
	        })
	    };
	    
  		$.ajax({
  			type: "POST",
            url: ajax_url_,
            data: requestBody,
        	contentType: 'application/x-www-form-urlencoded; charset=utf-8',
            async: false,
            dataType: "json",
            success: function(data){
            	ajax_query_citys_ = data.results;
             }
  		});
    	
    	return {citys:ajax_query_citys_};
  	},
  	refreshCitys: function(datas) {
  		this.setState({citys:datas})
  	},
	render: function() {
		var cl_list_ = (
			<div>
				<CityListTitle citys={this.state.citys} refreshCitys={this.refreshCitys.bind(this)}/>
				<CityListChild citys={this.state.citys} refreshCitys={this.refreshCitys.bind(this)}/>
			</div>
		);
		return cl_list_;
	}
});

ReactDOM.render( 
	<CityList /> ,
	document.getElementById('citylist-container')
);


function test(e){
	console.log(e);
}

