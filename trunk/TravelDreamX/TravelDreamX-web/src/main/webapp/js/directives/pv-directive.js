/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

'use strict';

travelDreamApp.directive('pv', function(){
    return {
        restrict : 'E', 
        templateUrl :  'templates/directive/pv.html',
        scope : {      
            pv : "="
        }
    };
});
