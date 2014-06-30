				<div class="modal-header">
            		<h3 class="modal-title"><b>I'm a modal!</b></h3>
        		</div>
        		<div class="modal-body">
            		<ul>
                		<li ng-repeat="item in items">
                    		<a ng-click="selected.item = item">{{ item }}</a>
                		</li>
            		</ul>
            		Selected: <b>{{ selected.item }}</b>
				</div>
        		<div class="modal-footer">
            		<button class="btn btn-success" ng-click="ok()">OK</button>
            		<button class="btn btn-primary" ng-click="more()">More</button>
            		<button class="btn btn-warning" ng-click="cancel()">Cancel</button>
        		</div>
