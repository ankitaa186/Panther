var pantherApp = angular.module("panther-app", []);	

var mainController = function Hello($scope, $http) {
		$http.get('http://localhost:8080/Panther/resources/item/').success(
				function(data) {
					$scope.items = data;
				});
		
		$scope.remove=function(itemid){
			// alert("delteing"+itemid);
			$http.delete('http://localhost:8080/Panther/resources/item/'+itemid).success(
					function(test){
					// alert("Deleted Item -" +
					// $scope.deleteMe);
					$http.get('http://localhost:8080/Panther/resources/item/').success(
							function(data) {
								$scope.items = data;
							});
					}
					
			);
				
	};
	
	$scope.add=function(){
		// alert("delteing"+itemid);
		var postData = {user: 'root', title: $scope.inputTitle, desc: $scope.inputDesc, due_date: '453453'}
		
		$http({
		    method: 'POST',
		    url: 'http://localhost:8080/Panther/resources/item/',
		    data: 'user=root&title='+$scope.inputTitle+'&desc='+$scope.inputDesc+'&due_date=35345',
		    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		}).success(
				function(test){
				// alert(test);
				$http.get('http://localhost:8080/Panther/resources/item/').success(
						function(data) {
							$scope.items = data;
						});
				}
				
		);
		$scope.inputTitle ="";
		$scope.inputDesc="";
			
};
	
		$scope.deleteItem=function(){
			// alert("delteing"+$scope.deleteMe);
			$http.delete('http://localhost:8080/Panther/resources/item/'+$scope.deleteMe).success(
					function(test){
					alert("Deleted Item -" + $scope.deleteMe);
					$http.get('http://localhost:8080/Panther/resources/item/').success(
							function(data) {
								$scope.items = data;
							});
					}
					
			);
		};
		
		$scope.completeItem=function(itemId){
			// alert("delteing"+$scope.deleteMe);
			$http({
		    method: 'POST',
		    url: 'http://localhost:8080/Panther/resources/item/'+itemId,
		    data: 'name=isCompleted&value=true',
		    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		}).success(
					function(test){
					$http.get('http://localhost:8080/Panther/resources/item/').success(
							function(data) {
								$scope.items = data;
							});
					}
					
			);
		};
		
		$scope.showCompletedBool=true;
		$scope.selectedItemId=-1;
		
		$scope.hideCompleted=function(){
			if($scope.showCompletedBool == true){
				$scope.showCompletedBool = false;
			}else{
				$scope.showCompletedBool = true;
			}
		};
		
		$scope.selectItem=function(itemId){
			$scope.selectedItemId= itemId;
			
						$http.get('http://localhost:8080/Panther/resources/item/'+itemId).success(
								function(data) {
									$scope.selectedItem = data;
								});
			
		}
		
		
	};

pantherApp.controller("mainController", mainController);
