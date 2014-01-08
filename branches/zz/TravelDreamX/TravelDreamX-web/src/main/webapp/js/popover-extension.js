angular.module( 'ui.bootstrap.popover' )
.directive( 'popoverTemplatePopup', [ '$templateCache', '$compile', function ( $templateCache, $compile ) {
  return {
    restrict: 'EA',
    replace: true,
    scope: { title: '@', content: '@', placement: '@', animation: '&', isOpen: '&' },
    templateUrl: 'templates/popover/popover-template.html',
    link: function( scope, iElement ) {
 
      var content = angular.fromJson( scope.content ),
          template = $templateCache.get( content.templateUrl ),
          templateScope = scope,
          scopeElements = document.getElementsByClassName( 'ng-scope' );
 
      angular.forEach( scopeElements, function( element ) { 
        var aScope = angular.element( element ).scope();
        if ( aScope.$id == content.scopeId ) {
          templateScope = aScope;
        }
      });
 
      iElement.find('div.popover-content').html( $compile( template )( templateScope ) );
    }
  };
}])
.directive( 'popoverTemplate', [ '$tooltip', function ( $tooltip ) {
  var tooltip = $tooltip( 'popoverTemplate', 'popover', 'click' );
 
  tooltip.compile = function() {
    return {
      'pre': function( scope, iElement, iAttrs ) {
        iAttrs.$set( 'popoverTemplate', { templateUrl: iAttrs.popoverTemplate, scopeId: scope.$id } );
      },
      'post': tooltip.link
    };
  };
 
  return tooltip;
}]);