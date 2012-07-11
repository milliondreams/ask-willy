/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/26/12
 * Time: 6:14 AM
 * To change this template use File | Settings | File Templates.
 */

//Skill Model
Model.Skill=Backbone.Model.extend({

})

//Skill Collection
Model.Skills=Backbone.Collection.extend({
    url:"/api/skills",

    //Reference to this collections model
    model:Model.Skill
})