/* globals beforeAll, afterAll, browser */
describe('mccloud', function describeMccloud() {
  beforeAll(function beforeDescribe(done) {
    browser
      .url('/home')
      .call(done);
  });
  afterAll(function afterDescribe(done) {
    browser.end(done);
  });
  describe('home', function describeHelloView() {
    it('should get js module', function jsModule(done) {
      browser.getText('.js-module p').then(function textCb(text) {
        expect(text).toBe('This was added from app/hello/world');
      }).call(done);
    });
  });
});
