/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

'use strict';

travelDreamApp.directive('visita', function(){
    return {
        restrict : 'E', 
        templateUrl :  'templates/directive/visita.html',
        scope : {      
            visita : "="
        }
    };
});
