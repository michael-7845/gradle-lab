package groovy.demo

/**
 * Created by I340951 on 10/30/2017.
 */
def text = '''\
Dear <% out.print firstname %> ${lastname},

We <% if (accepted) out.print 'are pleased' else out.print 'regret' %> \
to inform you that your paper entitled
'$title' was ${ accepted ? 'accepted' : 'rejected' }.

The conference committee.'''

def template = new groovy.text.StreamingTemplateEngine().createTemplate(text)

def binding = [
        firstname : "Grace",
        lastname  : "Hopper",
        accepted  : true,
        title     : 'Groovy for COBOL programmers'
]

String response = template.make(binding)

println response

assert response == '''Dear Grace Hopper,

We are pleased to inform you that your paper entitled
'Groovy for COBOL programmers' was accepted.

The conference committee.'''

template = new groovy.text.StreamingTemplateEngine().createTemplate("abc 123")
binding = [:]
println template.make(binding).class
println template.make(binding).toString()
println template.make(binding).toString().class
