/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/30/12
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */

Model.EmploymentRec=Backbone.Model.extend({

});

Model.EmpRecList=Backbone.Collection.extend({
    url:"/api/employment",
    model:Model.EmploymentRec

})
