# release-manager
Please run the following script in mongoDB before running the application\
`use test`\
`db.system_version.insert({_id: "systemVersion",version: 0})`
