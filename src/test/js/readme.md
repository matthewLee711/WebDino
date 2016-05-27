## How To Write Unit Tests

FED uses Jasmine BDD. We use `describe` and `it` functions to organize our unit tests. We also commonly use `beforeEach` and `afterEach` to setup and teardown tests


### Describe 
The important thing to note is that it statements are children of the parent describe. Dont just through write `it` method calls to one parent. For example:

***Good example***
```Javascript
// buy-box.js
describe('buybox', function(){
    // pretending the buybox method of add to cart pushes some object into an array like:  
    var cart = {
        items: [/*{...}*/]
    }
    beforeEach(function(){
        //setup the buybox in the dom
    });

    describe('add to cart', function(){
        it('should add item to cart on click', function(){
            $('.add-to-cart').click();
            //assuming cart.items
            expect(cart.items.length).toBe(1);
        });
        it('some other test about add to cart', function(){
            //...
        });
    });
});
```

***Bad Example***
```javascript
describe('buybox.js', funciton(){
    it('should increment on click', function(){
        //..code
    });
    it('should navigate to storefinder on zip code', function(){
        //..code
    });
    it('should do something else unrelated', function(){
        //..code
    });
});
```

You should be nesting the describe statements in a logical tree so that it makes sense.

### It
Each it statements need to invoke a single method you are testing. Other methods that are called from that method should be mocked using a [spy](http://jasmine.github.io/2.0/introduction.html#section-Spies)
```javascript
describe('buybox.js', funciton(){
    it('should increment on click', function(){
        spyOn($, 'ajax').and.callFake(function(){
            var $dfd = new $.Deferred();
            $dfd.resolve({mockResponse: true});
        });
        buyBox.addToCart();
        expect($.ajax).toHaveBeenCalledWith({url: 'the_expeted url'});
    });
});
```
