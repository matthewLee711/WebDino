/* globals runs,waitsFor */
define(['app/main'], function mainSpec(main) {
  describe('main should load', function describeMain() {
    it('should be defined', function itDef() {
      expect(main).toBeDefined();
    });
  });
});
