/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/26/12
 * Time: 7:06 AM
 * To change this template use File | Settings | File Templates.
 */

//Requirement Model
Model.Requirement=Backbone.Model.extend({

})

//Requirement Collection
Model.RequirementList=Backbone.Collection.extend({
    url:"/requirements",

    //Reference to this collections model
    model:Model.Requirement
})