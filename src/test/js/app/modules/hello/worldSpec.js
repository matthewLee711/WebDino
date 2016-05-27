/* globals runs,waitsFor */
define(['app/modules/hello/world', 'jquery'], function world(helloWorld, $) {
  describe('modules should loua', function describeLoad() {
    it('should be defined', function itLoad() {
      expect(helloWorld).toBeDefined();
    });
    it('should return init method', function itInit() {
      expect(helloWorld.init).toBeDefined();
    });
    it('should populate the $el with text', function itText() {
      var $el = $('<div></div>');
      helloWorld.init($el);
      expect($el.text()).toEqual('This was added from app/hello/world');
    });
  });
});
