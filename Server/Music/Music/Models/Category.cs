//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Music.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class Category
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Category()
        {
            this.Song_Category = new HashSet<Song_Category>();
        }
    
        public long ID { get; set; }
        public string Name { get; set; }
        public string Detail { get; set; }
        public string ImagePath { get; set; }
        public string CustomString1 { get; set; }
        public string CustomString2 { get; set; }
        public string CustomString3 { get; set; }
        public string CustomString4 { get; set; }
        public string CustomString5 { get; set; }
        public Nullable<int> CustomInt1 { get; set; }
        public Nullable<int> CustomInt2 { get; set; }
        public Nullable<int> CustomInt3 { get; set; }
        public Nullable<int> CustomInt4 { get; set; }
        public Nullable<int> CustomInt5 { get; set; }
        public Nullable<bool> CustomBool1 { get; set; }
        public Nullable<bool> CustomBool2 { get; set; }
        public Nullable<bool> CustomBool3 { get; set; }
        public Nullable<bool> CustomBool4 { get; set; }
        public Nullable<bool> CustomBool5 { get; set; }
    
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Song_Category> Song_Category { get; set; }
    }
}
