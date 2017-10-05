package pl.training.groovy.scripts

import groovy.xml.MarkupBuilder

import javax.xml.soap.Node

def data = '''
<users type="work">
    <user>
       <name>jkowalski</name>
       <email type="work">jan@wp.pl</email> 
    </user>
     <user>
       <name>mnowak</name>
       <email >marek@wp.pl</email> 
    </user>
</users>
'''

def users = new XmlParser().parseText(data)

println users.user.email.text()
println users instanceof groovy.util.Node
println users.attribute("type")
List<Node> usersNodes = users.children()
println usersNodes[0].children()[1].attribute("type")

String books = '''
    <response version-api="2.0">
        <value>
            <books>
                <book available="20" id="1">
                    <title>Don Xijote</title>
                    <author id="1">Manuel De Cervantes</author>
                </book>
                <book available="14" id="2">
                    <title>Catcher in the Rye</title>
                   <author id="2">JD Salinger</author>
               </book>
               <book available="13" id="3">
                   <title>Alice in Wonderland</title>
                   <author id="3">Lewis Carroll</author>
               </book>
               <book available="5" id="4">
                   <title>Don Xijote</title>
                   <author id="4">Manuel De Cervantes</author>
               </book>
           </books>
       </value>
    </response>
'''

def response = new XmlParser().parseText(books)


def writer = new StringWriter()
def xml = new MarkupBuilder(writer)
def source =  response.'**'
def booksElement = response.children()[0].children()[0]

def byId = { it ->
    it.@id == '3'
}

def getBookTitle = { book ->
    book.children()[0].text()
}

xml.books() {
    booksElement.each {
        book(title: getBookTitle(it), id: source.find(byId))
    }
}

println writer
