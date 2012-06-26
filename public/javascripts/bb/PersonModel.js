/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/25/12
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
 */

window.Model=new Object();

//Person Model
Model.Person=Backbone.Model.extend({
    //default attributes when a new person is added
    defaults:function(){
        return{
            employee:true,
            available:true
        }
    },
    //toggle the 'employee' attribute - whether the person is an employ of the company or not
    toggleEmployee:function(){
        this.save({employee:!this.get("employee")});
    },
    //toggle the 'available' state of the person
    toggleAvailable:function(){
        this.save({available:!this.get("available")});
    }
})

//Person Collection
Model.People=Backbone.Collection.extend({
    url:"/people",

    //Reference to this collections model
    model:Model.Person

})