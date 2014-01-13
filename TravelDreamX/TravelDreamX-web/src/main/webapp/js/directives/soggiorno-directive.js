/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

'use strict';

travelDreamApp.directive('soggiorno', function(){
    return {
        restrict : 'E', 
        templateUrl :  'templates/directive/soggiorno.html',
        scope : {      
            soggiorno : "="
        }
    };
});
