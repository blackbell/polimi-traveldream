/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
travelDreamApp.filter('range', function() {
  return function(input, total) {
    total = parseInt(total);
    for (var i=0; i<total; i++)
      input.push(i);
    return input;
  };
});

