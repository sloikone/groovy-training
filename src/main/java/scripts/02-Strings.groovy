package scripts

def name = 'Mruczek'
def age = 5

def text = 'Ala ma kota o imieniu ${name}'
def textWithInterpolation = "Ala ma kota o imieniu ${name}"
def multilineText = '''\
Ala ma kota o imieniu:
${name}
'''
def multilineTextWithInterpolation = """\
Ala ma kota o imieniu:
${name}, który ma ${-> age} lat
"""
def regex = /d{2}-d{3}\d/
def regexTwo = $/d{2}-d{3}d/$

age++

println text
println textWithInterpolation
println multilineText
println multilineTextWithInterpolation