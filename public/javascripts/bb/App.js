/**
 * Created with IntelliJ IDEA.
 * User: shiti
 * Date: 6/27/12
 * Time: 8:24 AM
 * To change this template use File | Settings | File Templates.
 */

$(function(){
    ko.numericObservable = function(initialValue) {
        var _actual = ko.observable(initialValue);

        var result = ko.dependentObservable({
            read: function() {
                return _actual();
            },
            write: function(newValue) {
                var parsedValue = parseFloat(newValue);
                _actual(isNaN(parsedValue) ? newValue : parsedValue);
            }
        });

        return result;
    };

    Model.People=new Model.PersonList;
    window.View=new Object();

    View.View_Person={
        name:ko.observable(''),
        designation:ko.observable(''),
        email:ko.observable(''),
        possible_genders:ko.observableArray(['MALE','FEMALE']),
        gender:ko.observable('MALE'),
        location:ko.observable(''),
        experience:ko.numericObservable(0),
        onAdd:function(){
            Model.People.create({name:this.name(),designation:this.designation(),email:this.email(),gender:this.gender(),location:this.location(),experience:this.experience()});
            this.name('');
            this.designation('');
            this.email('');
            this.gender('MALE');
            this.location('');
        }
    }

    ko.applyBindings(View.View_Person);
});
