type deletion = {deleted:    Date,
                 deleted_by: string};

var record: {id:       number,
             deletion: deletion | null}
                                                        
record = {id:       1,
          deletion: {deleted:    new Date("2022-03-25"),
                     deleted_by: "Logan"}}
