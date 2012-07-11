/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/26/12
 * Time: 6:04 AM
 * To change this template use File | Settings | File Templates.
 */

//Project Model
Model.Project=Backbone.Model.extend({

})

//Project Collection
Model.ProjectList=Backbone.Collection.extend({
    url:"/api/people",
    //Reference to this collections model
    model:Model.People

})